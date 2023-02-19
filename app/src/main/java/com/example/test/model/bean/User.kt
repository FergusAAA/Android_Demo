package com.example.test.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.model.bean
 * @class:       User
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/9/24 14:48
 * @author:       xuguangdong
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}