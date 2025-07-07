package com.project.e_commerce.android.domain.usecase

class CheckPasswordValidation {

    operator fun invoke(password: String): Pair<Boolean, String> {
        val minLength = 8
        val uppercasePattern = Regex(".*[A-Z].*")
        val lowercasePattern = Regex(".*[a-z].*")
        val digitPattern = Regex(".*[0-9].*")
        val specialCharPattern = Regex(".*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")

        return when {
            password.length < minLength -> Pair(true, "يجب أن تكون كلمة المرور على الأقل $minLength أحرف")
            !uppercasePattern.containsMatchIn(password) -> Pair(true, "يجب أن تحتوي كلمة المرور على حرف كبير واحد على الأقل")
            !lowercasePattern.containsMatchIn(password) -> Pair(true, "يجب أن تحتوي كلمة المرور على حرف صغير واحد على الأقل")
            !digitPattern.containsMatchIn(password) -> Pair(true, "يجب أن تحتوي كلمة المرور على رقم واحد على الأقل")
            !specialCharPattern.containsMatchIn(password) -> Pair(true, "يجب أن تحتوي كلمة المرور على حرف خاص واحد على الأقل")
            else -> Pair(false, "كلمة المرور صالحة")
        }
    }
}