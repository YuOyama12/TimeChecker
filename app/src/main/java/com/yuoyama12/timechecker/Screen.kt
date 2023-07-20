package com.yuoyama12.timechecker

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object ResultList : Screen("resultList")
}
