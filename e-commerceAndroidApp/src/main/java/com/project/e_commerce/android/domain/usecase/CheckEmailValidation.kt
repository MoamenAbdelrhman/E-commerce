package com.project.e_commerce.android.domain.usecase

class CheckEmailValidation {
    operator fun invoke(email: String): Pair<Boolean, String> {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return if (email.matches(emailRegex.toRegex())) {
            Pair(false, "")
        } else {
            Pair(true, "البريد الالكتروني غير صحيح")
        }
    }
}