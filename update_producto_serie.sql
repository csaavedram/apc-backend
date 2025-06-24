-- Script para actualizar la base de datos con los nuevos campos de ProductoSerie

-- Agregar las nuevas columnas si no existen
ALTER TABLE producto_serie 
ADD COLUMN IF NOT EXISTS cantidad_original INT DEFAULT NULL,
ADD COLUMN IF NOT EXISTS cantidad_vendida INT DEFAULT 0;

-- Actualizar los registros existentes
-- Establecer cantidadOriginal igual a cantidad para registros que no la tienen
UPDATE producto_serie 
SET cantidad_original = cantidad 
WHERE cantidad_original IS NULL;

-- Inicializar cantidad_vendida en 0 para registros que no la tienen
UPDATE producto_serie 
SET cantidad_vendida = 0 
WHERE cantidad_vendida IS NULL;

-- Para las series que ya están vendidas, establecer cantidad_vendida igual a cantidadOriginal
UPDATE producto_serie 
SET cantidad_vendida = cantidad_original,
    cantidad = 0
WHERE estado = 'VENDIDO' AND cantidad_vendida = 0;

-- Verificar los cambios
SELECT 
    numero_serie,
    estado,
    cantidad,
    cantidad_original,
    cantidad_vendida,
    fecha_creacion,
    fecha_venta
FROM producto_serie 
ORDER BY fecha_creacion DESC;
