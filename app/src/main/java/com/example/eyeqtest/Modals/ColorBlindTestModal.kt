package com.example.eyeqtest.Modals

data class ColorBlindTestModal(
    var userId: String? = null,
    var testId: String? = null,
    var testDate: String? = null,
    var testTime: String? = null,
    var RightResult: String? = null,
    var RightComment: String? = null,
    var leftResult: String? = null,
    var leftComment: String? = null,
)
