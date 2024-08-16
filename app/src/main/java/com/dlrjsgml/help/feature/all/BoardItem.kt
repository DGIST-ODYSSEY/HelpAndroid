package com.dlrjsgml.help.feature.all

import com.dlrjsgml.help.feature.home.upload.UpLoadState

data class BoardItem(
    val boards: List<UpLoadState> = arrayListOf(
        UpLoadState(
            title = "구지면에 맥도날드가 생겼으면 좋겠어요",
            content = "이공간에 멕도날드가 생겼으면 정말 좋겠어요",
            locate = "대구시 달성군",
            like = 132,
        ),
        UpLoadState(
            title = "대구소프트웨어마이스터고등학교 근처에 음식점이 생겼으면 좋겠어요",
            content = "아파트는 많은데 음식점이 적네요",
            locate = "대구시 달성군",
            like = 92
        ),
        UpLoadState(
            title = "테크노폴리스 근처에 미용실이 생겼으면 좋겠어요",
            content = "사용할 의향 있습니다.",
            locate = "대구시 달성군",
            like = 71
        ),
        UpLoadState(
            title = "디지스트 근처에 치킨집이 생겼으면 좋겠어요",
            content = "dddf",
            locate = "정말 편리할것 같네요",
            like = 0
        ),
        UpLoadState(
            title = "대구소프트웨어마이스터고등학교 근처에 카페가 생겼으면 정말 좋겠어요",
            content = "카페가 정말 부족한것 같아요 ㅠㅠ",
            locate = "대구시 달성군",
            like = 0
        )
    )
)
