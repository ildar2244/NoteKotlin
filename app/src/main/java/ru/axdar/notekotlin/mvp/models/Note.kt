package ru.axdar.notekotlin.mvp.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import ru.axdar.notekotlin.utils.formatDate
import java.util.*

/**
 * Created by ildar2244 on 18.09.2018.
 */

@Table(name = "Notes")
class Note : Model {
    @Column(name = "title")
    public var title: String? = null
    @Column(name = "text")
    public var text: String? = null
    @Column(name = "create_date")
    public var createDate: Date? = null
    @Column(name = "change_date")
    public var changeDate: Date? = null

    constructor(title: String?, createDate: Date?) : super() {
        this.title = title
        this.createDate = createDate
        this.changeDate = changeDate
    }

    constructor()

    fun getInfo(): String = "Название\n$title\n" +
            "Время создания: \n${formatDate(createDate)}\n" +
            "Время изменения: \n${formatDate(changeDate)}"
}