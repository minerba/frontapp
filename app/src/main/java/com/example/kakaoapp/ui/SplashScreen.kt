package com.example.kakaoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.foundation.Canvas
import com.example.kakaoapp.ui.theme.KakaoYellow

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KakaoYellow),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 카카오 말풍선 아이콘
            Canvas(modifier = Modifier.size(44.dp)) {
                drawKakaoBubble()
            }
            // pay 텍스트
            Text(
                text = "pay",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                letterSpacing = (-1).sp
            )
        }
    }
}

private fun DrawScope.drawKakaoBubble() {
    val w = size.width
    val h = size.height
    val path = Path().apply {
        // 말풍선 몸통 (둥근 타원형)
        moveTo(w * 0.5f, h * 0.04f)
        cubicTo(w * 0.08f, h * 0.04f, w * 0.0f, h * 0.32f, w * 0.0f, h * 0.5f)
        cubicTo(w * 0.0f, h * 0.68f, w * 0.08f, h * 0.82f, w * 0.22f, h * 0.88f)
        // 꼬리 부분
        lineTo(w * 0.18f, h * 1.0f)
        lineTo(w * 0.42f, h * 0.88f)
        // 오른쪽
        cubicTo(w * 0.78f, h * 0.88f, w * 1.0f, h * 0.7f, w * 1.0f, h * 0.5f)
        cubicTo(w * 1.0f, h * 0.32f, w * 0.92f, h * 0.04f, w * 0.5f, h * 0.04f)
        close()
    }
    drawPath(path = path, color = Color.Black)
}
