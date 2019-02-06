package com.ezmall.models

import java.util.*

data class Issue(val id: String,val type: String, val registeredOn: Date, val status: IssueStatus)