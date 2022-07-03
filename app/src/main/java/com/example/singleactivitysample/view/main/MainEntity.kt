package com.example.singleactivitysample.view.main

data class Message(
    val head: String,
    val body: String,
    var open: Boolean
)

// UI 확인을 위해 임시로 넣어 둔 Data 입니다.
var itemList = listOf(
    Message(
        "A1",
        "b1 app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\" " +
                "app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\"",
        true
    ),
    Message(
        "A2",
        "b1 app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\" " +
                "app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\"",
        true
    ),
    Message(
        "A3",
        "b1 app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\" " +
                "app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\"",
        false
    ),
    Message("A4", "b4", false),
    Message("A5", "b5", false),
    Message("A6", "b6", false),
    Message("A7", "b7", false),
    Message("A8", "b8", false),
    Message("A9", "b9", false),
    Message("A0", "b0", false),
    Message("A1", "b1", false),
    Message(
        "A2",
        "b1 app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\" " +
                "app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\"",
        false
    ),
    Message("A3", "b3", false),
    Message("A4", "b4", false),
    Message("A5", "b5", false),
    Message("A6", "b6", false),
    Message("A7", "b7", false),
    Message("A8", "b8", false),
    Message("A9", "b9", false),
    Message(
        "A0",
        "b1 app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\" " +
                "app:layout_constraintBottom_toBottomOf=\"parent\" app:layout_constraintBottom_toBottomOf=\"parent\"",
        true
    ),
    Message("A1", "b1", false),
    Message("A2", "b2", false),
    Message("A3", "b3", false),
    Message("A4", "b4", false),
    Message("A5", "b5", false),
    Message("A6", "b6", false),
    Message("A7", "b7", false),
    Message("A8", "b8", false),
    Message("A9", "b9", false),
    Message("A0", "b0", false),
)