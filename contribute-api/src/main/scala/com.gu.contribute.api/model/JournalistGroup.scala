package com.gu.contribute.api.model

import com.gu.management.Loggable

case class JournalistGroup(name: String,
    members: List[String]) extends Loggable

object JournalistGroup extends Loggable {

}