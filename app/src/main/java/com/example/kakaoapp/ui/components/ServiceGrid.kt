package com.example.kakaoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.R
import com.example.kakaoapp.ui.theme.*

@Composable
fun ServiceGrid(onCreditClick: () -> Unit = {}) {
    data class ServiceItem(val label: String, val onClick: () -> Unit, val content: @Composable () -> Unit)

    val services = listOf(
        ServiceItem("결제", {}) { BarcodeIconView() },
        ServiceItem("신용관리", onCreditClick) { PngIcon(R.drawable.icon_credit) },
        ServiceItem("대출쿠폰", {}) { PngIcon(R.drawable.icon_loan) },
        ServiceItem("카드발급", {}) { PngIcon(R.drawable.icon_card_issue) },
        ServiceItem("내 카드값", {}) { PngIcon(R.drawable.icon_card_bill) },
        ServiceItem("비상금대출", {}) { PngIcon(R.drawable.icon_emergency) },
        ServiceItem("손해보험", {}) { PngIcon(R.drawable.icon_insurance) },
        ServiceItem("더보기", {}) { MoreIconView() },
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(20.dp)
    ) {
        services.chunked(4).forEachIndexed { rowIndex, rowItems ->
            if (rowIndex > 0) Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                rowItems.forEach { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { item.onClick() }
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(52.dp),
                            contentAlignment = Alignment.Center
                        ) { item.content() }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(item.label, color = TextGray, fontSize = 11.sp, textAlign = TextAlign.Center, lineHeight = 15.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun PngIcon(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier.size(46.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun BarcodeIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(CardDarker), contentAlignment = Alignment.Center) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.CenterVertically) {
            listOf(3, 1, 4, 1, 2, 1, 4).forEach { w ->
                Box(modifier = Modifier.width(w.dp).height(22.dp).background(Color(0xFF888888)))
            }
        }
    }
}

@Composable
private fun MoreIconView() {
    Box(modifier = Modifier.size(52.dp).clip(CircleShape).background(CardDarker), contentAlignment = Alignment.Center) {
        Text("∨", color = TextGray, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
