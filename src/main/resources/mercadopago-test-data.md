# MercadoPago Test Data for Peru

## Test Cards for Peru
- **Visa:** 4013 5406 8274 6260
- **Mastercard:** 5031 7557 3453 0604
- **American Express:** 3711 803032 57522

## Test User Data
- **Email:** test_user_19653727@testuser.com
- **CPF/Document:** 12345678901

## Security Code
- **CVV:** 123 (for approved payments)
- **CVV:** 106 (for rejected payments)

## Expected Token Format
- Length: 32 characters
- Format: Hexadecimal (a-f, 0-9)
- Example: cd52d4eb0062267e3e6a28324f915b97

## Common Error Causes
1. **internal_error (500):**
   - Token generated with different public key than access token
   - Invalid token format
   - Expired token
   - Currency mismatch (should be PEN for Peru)

2. **Authorization errors (401):**
   - Invalid access token
   - Mismatched credentials

3. **Bad request (400):**
   - Missing required fields
   - Invalid data format
