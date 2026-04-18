package com.example.kakaoapp.ui

import androidx.compose.foundation.Image
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

private val HistoryBlue = Color(0xFF4A9EFF)
private val ScoreUp = Color(0xFF4ADE80)
private val ScoreDown = Color(0xFFFF6B6B)

private fun getLoanRate(score: Int): String = when {
    score >= 900 -> "최저 연 2.9%"
    score >= 850 -> "최저 연 3.5%"
    score >= 800 -> "최저 연 4.2%"
    score >= 750 -> "최저 연 5.1%"
    score >= 700 -> "최저 연 6.8%"
    score >= 650 -> "최저 연 8.5%"
    else -> "최저 연 10% 이상"
}

private sealed class HistoryItem {
    data class Loan(val date: String, val title: String, val desc: String, val hasCard: Boolean) : HistoryItem()
    data class Score(val date: String, val change: Int, val from: Int, val to: Int, val agency: String) : HistoryItem()
}

private data class HistorySection(val year: String, val items: List<HistoryItem>)

private val KCB_HISTORY = listOf(
    HistorySection("2026년", listOf(
        HistoryItem.Score("3. 10.", -12, 769, 757, "KCB"),
        HistoryItem.Loan("2. 15.", "대출 잔액 변동", "한국스탠다드차타드은행 대출잔액이 변동됐어요", true),
    )),
    HistorySection("2025년", listOf(
        HistoryItem.Score("11. 5.", +7, 762, 769, "KCB"),
        HistoryItem.Loan("10. 20.", "대출 잔액 변동", "한국스탠다드차타드은행 대출잔액이 변동됐어요", false),
        HistoryItem.Score("8. 2.", -5, 767, 762, "KCB"),
    ))
)

private val NICE_HISTORY = listOf(
    HistorySection("2026년", listOf(
        HistoryItem.Loan("3. 26.", "대출 잔액 변동", "한국스탠다드차타드은행 대출잔액이 변동됐어요", true),
        HistoryItem.Score("3. 3.", -8, 884, 876, "NICE"),
    )),
    HistorySection("2025년", listOf(
        HistoryItem.Loan("12. 26.", "대출 잔액 변동", "한국스탠다드차타드은행 대출잔액이 변동됐어요", false),
        HistoryItem.Score("11. 12.", +5, 879, 884, "NICE"),
        HistoryItem.Loan("11. 26.", "대출 잔액 변동", "한국스탠다드차타드은행 대출잔액이 변동됐어요", false),
        HistoryItem.Score("9. 4.", -3, 882, 879, "NICE"),
    ))
)

@Composable
fun CreditHistoryScreen(
    scoreType: String,
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit = {},
    onTabChange: (String) -> Unit
) {
    val score = if (scoreType == "KCB") 757 else 876
    val history = if (scoreType == "KCB") KCB_HISTORY else NICE_HISTORY

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
                Text("조회·변동 내역", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            IconButton(onClick = onNavigateToHome) {
                Icon(Icons.Default.Home, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
            }
        }

        // 탭바
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("KCB", "NICE").forEach { tab ->
                val isSelected = scoreType == tab
                Box(
                    modifier = Modifier.weight(1f).clickable { onTabChange(tab) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            tab,
                            color = if (isSelected) TextWhite else TextGrayDark,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(if (isSelected) TextWhite else Color.Transparent))
                    }
                }
            }
        }
        HorizontalDivider(color = Color(0xFF1F2937))

        // 스크롤 콘텐츠
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // 대출 금리 배너
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardDark)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.icon_credit_score),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                    Column {
                        Text("${score}점으로 가능한", color = HistoryBlue, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Text("내 대출 최저 금리 보기", color = TextWhite, fontSize = 14.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(getLoanRate(score), color = TextGrayDark, fontSize = 12.sp)
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 연도별 히스토리
            history.forEach { section ->
                Text(section.year, color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.padding(start = 20.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    section.items.forEach { item ->
                        Box {
                            Box(
                                modifier = Modifier
                                    .offset(x = (-18).dp, y = 6.dp)
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4B5563))
                            )
                            when (item) {
                                is HistoryItem.Loan -> LoanItem(item)
                                is HistoryItem.Score -> ScoreItem(item)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun LoanItem(item: HistoryItem.Loan) {
    Column {
        Text(item.date, color = TextGrayDark, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(item.title, color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(item.desc, color = TextGray, fontSize = 14.sp)
        if (item.hasCard) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardDark)
                    .padding(16.dp)
            ) {
                Text("대출 잔액을 확인해보세요", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("상환 후 잔액을 확인하고 다음 상환일을 준비하세요.", color = TextGray, fontSize = 14.sp, lineHeight = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {},
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text("자세히 보기", fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun ScoreItem(item: HistoryItem.Score) {
    val isUp = item.change > 0
    Column {
        Text(item.date, color = TextGrayDark, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Text("신용점수 ", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text("${Math.abs(item.change)}점", color = if (isUp) ScoreUp else ScoreDown, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(" ${if (isUp) "상승" else "하락"}", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text("${item.agency} ${item.from}점 → ${item.to}점", color = TextGray, fontSize = 14.sp)
    }
}
