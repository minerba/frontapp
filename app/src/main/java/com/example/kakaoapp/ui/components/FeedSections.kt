package com.example.kakaoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.R
import com.example.kakaoapp.ui.theme.*

@Composable
fun BalanceFightSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("밸런스 파이트", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("🗳️", fontSize = 30.sp)
                Column {
                    Text("경기 몰입 깨는 끝판왕?", color = Color(0xFFFF9500), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Text("화면 버퍼링 vs 옆집 합성", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
            OutlinedButton(
                onClick = {},
                shape = CircleShape,
                border = androidx.compose.foundation.BorderStroke(1.dp, TextGrayDark),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text("확인", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun FortuneSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("오늘의 운세", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("🍀", fontSize = 30.sp)
                Column {
                    Text("4월 20일의 운세는?", color = Color(0xFFFF9500), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Text("띠별 행운지수 확인하기", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
            OutlinedButton(
                onClick = {},
                shape = CircleShape,
                border = androidx.compose.foundation.BorderStroke(1.dp, TextGrayDark),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text("확인", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun PsychTestSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("심리테스트", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // 조선시대 관상
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.test_joseon_face),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(90.dp).clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("조선시대 관상", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Text("테스트 시작", color = TextGrayDark, fontSize = 11.sp)
            }
            // 성격 네컷
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.test_personality_4cut),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(90.dp).clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.clip(RoundedCornerShape(3.dp)).background(CardDarker).padding(horizontal = 6.dp, vertical = 2.dp)) {
                    Text("New", color = TextWhite, fontSize = 9.sp)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text("성격 네컷", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Text("테스트 시작", color = TextGrayDark, fontSize = 11.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("더 많은 테스트 보기", color = Color(0xFF4A90D9), fontSize = 14.sp)
        }
    }
}

@Composable
fun PayAttentionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("페이어텐션", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(RedBadge))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("11시간 전", color = TextGrayDark, fontSize = 12.sp)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardDarker)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("🧮", fontSize = 24.sp)
            Column {
                Text("고유가 피해지원금, 나는 얼마나 받을까?", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text("신청 방법 확인해 보세요", color = TextGrayDark, fontSize = 12.sp)
            }
        }
        TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("더 많은 콘텐츠 보기", color = Color(0xFF4A90D9), fontSize = 14.sp)
        }
    }
}

@Composable
fun MiniGameSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("미니게임", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("2달 전", color = TextGrayDark, fontSize = 12.sp)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GameCard(modifier = Modifier.weight(1f), resId = R.drawable.minigame_infinity_dice, genre = "액션·아케이드", title = "인피니티 다이스")
            GameCard(modifier = Modifier.weight(1f), resId = R.drawable.minigame_where_is_woong, genre = "두뇌·퍼즐", title = "어디있나웅")
        }
    }
}

@Composable
private fun GameCard(modifier: Modifier, resId: Int, genre: String, title: String) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(genre, color = Color(0xFFFF9500), fontSize = 10.sp, fontWeight = FontWeight.Medium)
        Text(title, color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(32.dp),
            shape = CircleShape,
            border = androidx.compose.foundation.BorderStroke(1.dp, TextGrayDark),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Text("플레이", fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier.size(14.dp).clip(CircleShape).background(Color(0xFFFF6B00)),
                contentAlignment = Alignment.Center
            ) {
                Text("P", color = TextWhite, fontSize = 7.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun RecommendSection(userName: String = "나명환") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Text("${userName}님을 위한 추천", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))
        listOf(
            "🎮" to "100P 받고 게임하기",
            "🌼" to "행운의 복권",
            "🪙" to "함께하는 자산관리"
        ).forEach { (emoji, label) ->
            HorizontalDivider(color = Color(0xFF1F2937), thickness = 1.dp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(emoji, fontSize = 20.sp)
                    Text(label, color = TextWhite, fontSize = 14.sp)
                }
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(16.dp))
            }
        }
        TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("서비스 전체 보기", color = Color(0xFF4A90D9), fontSize = 14.sp)
        }
    }
}
