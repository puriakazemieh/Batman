package com.kazemieh.www.batman.ui.commen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kazemieh.www.batman.R

@Composable
fun Loading3Dots() {
    val composition by if (!isSystemInDarkTheme()) {
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading3dotsdark))
    } else {
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading3dots))
    }
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}