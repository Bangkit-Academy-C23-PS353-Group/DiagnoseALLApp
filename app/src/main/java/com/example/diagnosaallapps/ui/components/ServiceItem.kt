import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diagnosaallapps.R
import com.example.diagnosaallapps.model.Service

@Composable
fun ServiceItem(
    service: Service,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(140.dp).height(140.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(service.imageService),
                contentDescription = null,
                modifier = Modifier
                    .width(140.dp)
                    .height(76.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            )
            Text(
                text = service.textService,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier.padding(9.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ServiceItemPreview() {
    MaterialTheme() {
        ServiceItem(
            service = Service(R.drawable.bloodcheck, "Blood Check"),
        )
    }
}
