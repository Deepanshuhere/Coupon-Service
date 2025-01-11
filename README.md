# Coupon Service Project

This is a Spring Boot-based coupon service built using **Java 8**, **Spring Boot**, and **MongoDB**.

## Tech Stack
- **Java 8**
- **Spring Boot**
- **MongoDB**

## Features and Assumptions

- **Discount %** is an integer value.
- All amounts are assumed to be in integers.
- Discounts are **percentage-based** for both **cart-wise** and **product-wise** coupons.
- No maximum cap on the discount amount.
- A coupon can be applied **only once** in a cart.
- **Only one coupon** can be applied at a time.
- No **quantity limit** for products.
- By default, coupons **expire after a month**. Custom expiration time can be set.
- Assuming there is only **one coupon** of each type (can be extended to handle multiple coupons of the same type).
- **No limit** on the number of **Product Wise Coupons** (this can be set).
- Using **entity directly** instead of DTOs in request and response for coupons.
- **No validations** currently (plan to add validations to all endpoints).
- **Logging** should be present at necessary levels.
- **Soft Delete** feature to prevent accidental deletion and for backup purposes (helpful for data recovery).
- Using **multiple design patterns** for feature extensibility and better coding practices (e.g., Builder Design Pattern, Strategy Design Pattern, etc.).
- Can handle **BigDecimal** or **double** for handling larger numbers, but for the proof of concept (POC), integers are used.
- **Improvement Plan**:
  - Add more comments for better code understanding.
  - Create custom exceptions for handling different exceptions.
  - Set up global exception handling.
  - Follow **code-to-interface approach** for repositories to make the app database-independent (useful if we switch databases in the future).
  - Follow **code-to-interface approach** for the service layer.
  - Add **create** and **update** information to track the creation and modification details.
  - Add support for **adding multiple product-wise coupons** at once (POC handles just one coupon for now).
  - Add **unit test cases** or follow **TDD** design principles.
  - **Naming convention** for JSON requests and responses follows the `snake_case` style (using underscores).
