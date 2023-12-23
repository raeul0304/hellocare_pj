package com.example.hellocare_pj.model

import org.checkerframework.checker.signature.qual.IdentifierOrPrimitiveType

data class HealthBoardData(
    val h_img : String,
    val h_title : String
)
data class HealthData(
    var healthTitle : String,
    var healthInfo : String
):java.io.Serializable
fun main(){
    var healthinfo1 : HealthData = HealthData("Q. 빈대에 물리면 어떤 증상이 나타나나요?",
        "A. 빈대에 물리면 가려운 증상이 가장 먼저 나타나요. \n" +
                "가려움증과 함께 피부에 붉거나 흰색의 부어오르는 자국, 수포 등의 증상이 보이면 빈대에 물린 것으로 의심해 볼 수 있어요." +
                "빈대에 물린 자국은 모기 물린 자국과 유사하지만 빈대는 혈관을 잘 찾지 못하기 때문에 2~3곳을 연달아 물어 일렬이나 원형으로 자국이 나타나는 특징이 있어요" +
                "" +
                "빈대 물렷을 때 증상 : " +
                "가려움증과 통징이 동반된다. " +
                "물린 자국이 2~3곡의 일렬이나 원형으로 나타난다." +
                "물린 자국에서 노린내나 곰팡이 냄새가 느껴진다" +
                "" +
                "빈대에 물렸을 때는 처음에 잘 모르고 넘어갈 수 있으며 하루에서 최대 3일까지의 잠복기 이후에 가려움증이 나타날 수 있어요." +
                "따라서 만약 이와 같은 증상이 나타난다면, 최대한 긁지 말고 빠르게 피부과를 방문해 항히스타민제나 스테로이드 연고를 처방받아 적절히 치료하는 것이 도움이 돼요" +
                "물린 부위를 너무 심하게 긁을 경우에는 2차 감염의 위험이 있으므로 간지러워도 최대한 긁지 않도록 주의해야 해요" +
                "" +
                "만약 집에 빈대가 나타났다면 해충 방역 전문 업체를 통해 완전히 박멸하도록 하는 것이 좋으며, 환경부가 허가한 살추제를 사람 피부가 닿지 않는 곳에 뿌려 빈대의 확산을 막는 것이 필요해요" +
                "환경부가 허가한 살충제는 '빈대 퇴치약'을 검색했을 때 제품 상세 페이지 내에서 여부를 확인할 수 있어요")

    var healthinfo2: HealthData = HealthData("asdf", "asdfag")
}
