package com.example.kakaoapp.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kakaoapp.ui.theme.*

@Composable
fun CreditLoadingScreen(onBack: () -> Unit) {
    val shimmerColors = listOf(
        Color(0xFF2A2A2A),
        Color(0xFF3A3A3A),
        Color(0xFF2A2A2A),
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
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
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로", tint = TextWhite, modifier = Modifier.size(24.dp))
            }
            Icon(Icons.Default.Home, contentDescription = "홈", tint = TextWhite, modifier = Modifier.size(20.dp))
        }

        // 탭바 스켈레톤
        Row(modifier = Modifier.fillMaxWidth()) {
            repeat(2) {
                Box(modifier = Modifier.weight(1f).padding(vertical = 14.dp, horizontal = 40.dp)) {
                    Skeleton(brush = brush, modifier = Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(4.dp)))
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937))

        // 신용점수 스켈레톤
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(48.dp)) {
                repeat(2) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Skeleton(brush = brush, modifier = Modifier.width(40.dp).height(12.dp).clip(RoundedCornerShape(4.dp)))
                        Skeleton(brush = brush, modifier = Modifier.width(80.dp).height(38.dp).clip(RoundedCornerShape(6.dp)))
                    }
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937))

        // 기능 리스트 스켈레톤 3개
        repeat(3) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Skeleton(brush = brush, modifier = Modifier.size(28.dp).clip(CircleShape))
                    Skeleton(brush = brush, modifier = Modifier.width(120.dp).height(14.dp).clip(RoundedCornerShape(4.dp)))
                }
                Skeleton(brush = brush, modifier = Modifier.width(50.dp).height(24.dp).clip(RoundedCornerShape(20.dp)))
            }
            HorizontalDivider(color = Color(0xFF1F2937))
        }

        // 남은 대출금 / 내 예상 대출 스켈레톤
        repeat(2) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Skeleton(brush = brush, modifier = Modifier.width(80.dp).height(14.dp).clip(RoundedCornerShape(4.dp)))
                Skeleton(brush = brush, modifier = Modifier.width(120.dp).height(14.dp).clip(RoundedCornerShape(4.dp)))
            }
            HorizontalDivider(color = Color(0xFF1F2937))
        }

        // 카드 2개 스켈레톤
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(2) {
                Skeleton(brush = brush, modifier = Modifier.weight(1f).height(90.dp).clip(RoundedCornerShape(16.dp)))
            }
        }
    }
}

@Composable
private fun Skeleton(brush: Brush, modifier: Modifier) {
    Box(modifier = modifier.background(brush))
}
