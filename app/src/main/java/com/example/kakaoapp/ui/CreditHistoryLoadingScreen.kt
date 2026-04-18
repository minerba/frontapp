package com.example.kakaoapp.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kakaoapp.ui.theme.*

@Composable
fun CreditHistoryLoadingScreen(onBack: () -> Unit) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )
    val brush = Brush.linearGradient(
        colors = listOf(Color(0xFF2A2A2A), Color(0xFF3A3A3A), Color(0xFF2A2A2A)),
        start = Offset(translateAnim - 200f, 0f),
        end = Offset(translateAnim, 0f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .systemBarsPadding()
    ) {
        // 헤더
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextWhite, modifier = Modifier.size(24.dp))
                }
                Box(modifier = Modifier.width(100.dp).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
            Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
        }

        // 탭바 스켈레톤
        Row(modifier = Modifier.fillMaxWidth()) {
            repeat(2) {
                Box(modifier = Modifier.weight(1f).padding(vertical = 14.dp, horizontal = 50.dp)) {
                    Box(modifier = Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937))

        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            // 금리 배너 스켈레톤
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(brush))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(modifier = Modifier.width(120.dp).height(12.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                    Box(modifier = Modifier.width(90.dp).height(12.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                }
            }

            // 연도 스켈레톤
            Box(modifier = Modifier.width(60.dp).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))

            // 타임라인 아이템 스켈레톤 3개
            Column(modifier = Modifier.padding(start = 20.dp), verticalArrangement = Arrangement.spacedBy(28.dp)) {
                repeat(3) { index ->
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(modifier = Modifier.width(40.dp).height(10.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                        Box(modifier = Modifier.width(100.dp).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                        Box(modifier = Modifier.width(220.dp).height(12.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                        if (index == 0) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(16.dp)).background(brush))
                        }
                    }
                }
            }
        }
    }
}
