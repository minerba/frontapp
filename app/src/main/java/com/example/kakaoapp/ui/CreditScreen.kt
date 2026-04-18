package com.example.kakaoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.kakaoapp.R
import com.example.kakaoapp.ui.theme.*

val BlueAccent = Color(0xFF4A9EFF)
val BlueAccentBg = Color(0xFF1A2A3A)

@Composable
fun CreditScreen(onBack: () -> Unit, onNavigateToHome: () -> Unit = {}, onNavigateToHistory: (String) -> Unit = {}) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextWhite, modifier = Modifier.size(24.dp))
            }
            IconButton(onClick = onNavigateToHome) {
                Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("신용관리", "내 대출 한도").forEachIndexed { index, title ->
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            modifier = Modifier.padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                title,
                                color = if (selectedTab == index) TextWhite else TextGrayDark,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTab == index) FontWeight.SemiBold else FontWeight.Normal
                            )
                            if (index == 1) Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(RedBadge))
                        }
                        Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(if (selectedTab == index) TextWhite else Color.Transparent))
                    }
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937), thickness = 1.dp)

        Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {

            // 신용점수
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
                    Column(modifier = Modifier.clickable { onNavigateToHistory("KCB") }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("KCB", color = Color(0xFFD1D5DB), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF9CA3AF), modifier = Modifier.size(14.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("757점", color = TextWhite, fontSize = 38.sp, fontWeight = FontWeight.Bold, letterSpacing = (-1).sp)
                    }
                    Column(modifier = Modifier.clickable { onNavigateToHistory("NICE") }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("NICE", color = Color(0xFFD1D5DB), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF9CA3AF), modifier = Modifier.size(14.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("876점", color = TextWhite, fontSize = 38.sp, fontWeight = FontWeight.Bold, letterSpacing = (-1).sp)
                    }
                }
            }
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

            Column {
                FeatureRow(
                    icon = {
                        Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(CardDarker), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(R.drawable.icon_refresh),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    },
                    title = "신용점수 자동 올리기",
                    badge = { BadgeChip("이용 중", TextGrayDark, CardDarker) }
                )
                HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))
                FeatureRow(
                    icon = {
                        Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(KakaoYellow), contentAlignment = Alignment.Center) {
                            Text("%", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    title = "금리인하 자동신청",
                    badge = { BadgeChip("새로 오픈", BlueAccent, BlueAccentBg) }
                )
                HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))
                FeatureRow(
                    icon = {
                        Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(Color(0xFF1DB954)), contentAlignment = Alignment.Center) {
                            Text("W", color = TextWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    title = "대출 이자 지원받기",
                    badge = { BadgeChip("확인하기", TextGrayDark, CardDarker) }
                )
            }
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

            SimpleRow(title = "남은 대출금", actionText = "최신 잔액 보기", actionColor = TextGrayDark)
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))
            SimpleRow(title = "내 예상 대출", actionText = "최대 50,000P 이자 지원금", actionColor = BlueAccent)
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("정부지원대출", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("진단하기", color = BlueAccent, fontSize = 14.sp)
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(14.dp))
                    }
                }
                Column(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("매달 갚는 이자", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(RedBadge), contentAlignment = Alignment.Center) {
                            Text("N", color = TextWhite, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("낮춰보기", color = BlueAccent, fontSize = 14.sp)
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(14.dp))
                    }
                }
            }
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

            // AD 배너
            Column(modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(16.dp)).background(CardDark).padding(16.dp)) {
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.icon_okt),
                        contentDescription = null,
                        modifier = Modifier.size(44.dp),
                        contentScale = ContentScale.Fit
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("내 명의 차가 있다면?", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                            Box(modifier = Modifier.clip(RoundedCornerShape(3.dp)).background(Color(0xFF374151)).padding(horizontal = 4.dp, vertical = 2.dp)) {
                                Text("AD", color = TextGrayDark, fontSize = 10.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("최대 한도 1억, 최장 84개월", color = TextGrayDark, fontSize = 12.sp)
                        Text("내차담보플러스OK론", color = TextGrayDark, fontSize = 12.sp)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF4B5563), modifier = Modifier.size(16.dp))
                }
                HorizontalDivider(modifier = Modifier.padding(top = 12.dp), color = Color(0xFF1F2937))
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "OK저축은행 준법감시인 심의필\n제2026-103호 (유효기간 : 2026.03.24 ~ 2027.03.23)",
                    color = Color(0xFF4B5563), fontSize = 10.sp, lineHeight = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FeatureRow(icon: @Composable () -> Unit, title: String, badge: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            icon()
            Text(title, color = TextWhite, fontSize = 14.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            badge()
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF4B5563), modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun BadgeChip(text: String, textColor: Color, bgColor: Color) {
    Box(modifier = Modifier.clip(CircleShape).background(bgColor).padding(horizontal = 10.dp, vertical = 4.dp)) {
        Text(text, color = textColor, fontSize = 12.sp)
    }
}

@Composable
private fun SimpleRow(title: String, actionText: String, actionColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = TextWhite, fontSize = 14.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(actionText, color = actionColor, fontSize = 14.sp)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(16.dp))
        }
    }
}
