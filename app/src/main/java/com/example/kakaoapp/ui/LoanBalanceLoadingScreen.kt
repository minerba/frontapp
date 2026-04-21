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
fun LoanBalanceLoadingScreen(onBack: () -> Unit) {
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
        modifier = Modifier.fillMaxSize().background(BgDark).systemBarsPadding()
    ) {
        // 헤더
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextWhite, modifier = Modifier.size(24.dp))
            }
            Box(modifier = Modifier.width(40.dp).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            IconButton(onClick = {}) {
                Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
            }
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

        // 우대금리 배너 스켈레톤
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(28.dp).clip(RoundedCornerShape(8.dp)).background(brush))
                Box(modifier = Modifier.width(140.dp).height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
            Box(modifier = Modifier.width(60.dp).height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
        }
        HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

        // 남은 잔액 스켈레톤
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier.width(60.dp).height(12.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            Box(modifier = Modifier.width(200.dp).height(36.dp).clip(RoundedCornerShape(6.dp)).background(brush))
        }

        // 정보 카드 2개 스켈레톤
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(2) {
                Box(modifier = Modifier.weight(1f).height(72.dp).clip(RoundedCornerShape(12.dp)).background(brush))
            }
        }

        // 신용점수 카드 스켈레톤
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp).height(64.dp).clip(RoundedCornerShape(12.dp)).background(brush))

        // 이자 낮추기 배너 스켈레톤
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp).height(72.dp).clip(RoundedCornerShape(12.dp)).background(brush))

        // 필터 탭 스켈레톤
        Row(modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(3) {
                Box(modifier = Modifier.width(60.dp).height(32.dp).clip(CircleShape).background(brush))
            }
        }

        // 대출 카드 스켈레톤 2개
        Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(2) {
                Box(modifier = Modifier.fillMaxWidth().height(90.dp).clip(RoundedCornerShape(16.dp)).background(brush))
            }
        }
    }
}
