package com.gu.contribute.api

import org.bson.types.ObjectId

package object dispatcher {
  implicit def stringToObjectId(s: String): ObjectId = new ObjectId(s)
}