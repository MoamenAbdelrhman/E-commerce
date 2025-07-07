package com.project.e_commerce.android.domain.usecase

class CheckLoginValidation {

    operator fun invoke(email: String?, password: String?): Pair<Boolean, String> {
        if (email == null || password == null) {
            return Pair(false, "Email and password must not be null")
        }
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)$")

        if (!emailPattern.matches(email)) {
            return Pair(false, "Invalid email format")
        }

        if (password.length < 8) {
            return Pair(false, "Password must be at least 8 characters long")
        }

        val containsNumber = password.any { it.isDigit() }

        if (!containsNumber) {
            return Pair(false, "Password must contain at least one number")
        }


        return Pair(true, "Login credentials are valid")
    }
}
