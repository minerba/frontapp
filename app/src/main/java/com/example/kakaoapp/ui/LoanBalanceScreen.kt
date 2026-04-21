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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

@Composable
fun LoanBalanceScreen(onBack: () -> Unit, onNavigateToHome: () -> Unit = {}) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .systemBarsPadding()
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextWhite, modifier = Modifier.size(24.dp))
            }
            Text("대출", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            IconButton(onClick = onNavigateToHome) {
                Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
            }
        }

        // Tab Bar
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("내 대출", "내 대출 한도").forEachIndexed { index, title ->
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

            // 우대금리 배너
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier.size(28.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFF3B82F6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("%", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("최대 3.1% 우대금리 가능", color = TextWhite, fontSize = 14.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("9일 남음", color = BlueAccent, fontSize = 14.sp)
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(16.dp))
                }
            }
            HorizontalDivider(color = Color(0xFF1F2937).copy(alpha = 0.6f))

            // 남은 잔액
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("남은 잔액", color = TextWhite, fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.Refresh, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
                        Text("조금 전", color = TextGrayDark, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("161,826,374원", color = TextWhite, fontSize = 36.sp, fontWeight = FontWeight.Bold, letterSpacing = (-1).sp)
            }

            // 정보 카드 2개
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 매달 갚는 돈
                Row(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(CardDark).padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier.size(28.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFF1DB954)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("W", color = TextWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text("매달 갚는 돈", color = TextGrayDark, fontSize = 11.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("약 81만원 예상", color = TextWhite, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
                        }
                    }
                }
                // 내 금리 비교
                Row(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(CardDark).padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier.size(28.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFF3B82F6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("%", color = TextWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text("내 금리 비교", color = TextGrayDark, fontSize = 11.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("평균보다 저렴함", color = BlueAccent, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }

            // 내 신용점수 카드
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardDark)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFF7C3AED)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("★", color = TextWhite, fontSize = 16.sp)
                }
                Column {
                    Text("내 신용점수", color = TextGrayDark, fontSize = 12.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("KCB 757점", color = BlueAccent, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(14.dp))
                    }
                }
            }

            // 자동으로 이자 낮추기
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E3A5F))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier.size(32.dp).clip(CircleShape).background(Color(0xFF3B82F6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("⚡", fontSize = 14.sp)
                        }
                        Text("자동으로 이자 낮추기", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("정부가 추진하는 무료 서비스", color = Color(0xFF93C5FD), fontSize = 12.sp)
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(16.dp))
            }

            // 필터 탭
            var selectedFilter by remember { mutableIntStateOf(0) }
            Row(
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("상환일순", "금리순", "만기일순").forEachIndexed { index, label ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (selectedFilter == index) TextWhite else CardDarker)
                            .clickable { selectedFilter = index }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            label,
                            color = if (selectedFilter == index) Color.Black else TextGrayDark,
                            fontSize = 12.sp,
                            fontWeight = if (selectedFilter == index) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }
            }

            // 대출 목록
            Column(
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 대출 1
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardDark)
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFF3B82F6)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🏠", fontSize = 20.sp)
                            }
                            Column {
                                Text("29,000,000원", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("120,843원 상환 예상", color = TextGrayDark, fontSize = 12.sp)
                                Text("주거래적금인대출(성정) 만기일시상환", color = Color(0xFF6B7280), fontSize = 12.sp)
                            }
                        }
                        Box(
                            modifier = Modifier.clip(CircleShape).background(Color(0xFF450A0A)).padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("내일", color = Color(0xFFEF4444), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                // 대출 2
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardDark)
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier.size(44.dp).clip(CircleShape).background(BlueAccent),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("₩", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text("132,826,374원", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("688,715원 상환 예상", color = TextGrayDark, fontSize = 12.sp)
                                Text("K H F C 주택자금대출", color = Color(0xFF6B7280), fontSize = 12.sp)
                            }
                        }
                        Text("4일 남음", color = TextGrayDark, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
