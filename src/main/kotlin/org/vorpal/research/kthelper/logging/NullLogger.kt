package org.vorpal.research.kthelper.logging

import org.slf4j.Logger
import org.slf4j.Marker

class NullLogger : Logger {
    override fun getName(): String = "null"
    override fun isTraceEnabled(): Boolean = false
    override fun isTraceEnabled(p0: Marker?): Boolean = false
    override fun trace(p0: String?) {}
    override fun trace(p0: String?, p1: Any?) {}
    override fun trace(p0: String?, p1: Any?, p2: Any?) {}
    override fun trace(p0: String?, vararg p1: Any?) {}
    override fun trace(p0: String?, p1: Throwable?) {}
    override fun trace(p0: Marker?, p1: String?) {}
    override fun trace(p0: Marker?, p1: String?, p2: Any?) {}
    override fun trace(p0: Marker?, p1: String?, p2: Any?, p3: Any?) {}
    override fun trace(p0: Marker?, p1: String?, vararg p2: Any?) {}
    override fun trace(p0: Marker?, p1: String?, p2: Throwable?) {}
    override fun isDebugEnabled(): Boolean = false
    override fun isDebugEnabled(p0: Marker?): Boolean = false
    override fun debug(p0: String?) {}
    override fun debug(p0: String?, p1: Any?) {}
    override fun debug(p0: String?, p1: Any?, p2: Any?) {}
    override fun debug(p0: String?, vararg p1: Any?) {}
    override fun debug(p0: String?, p1: Throwable?) {}
    override fun debug(p0: Marker?, p1: String?) {}
    override fun debug(p0: Marker?, p1: String?, p2: Any?) {}
    override fun debug(p0: Marker?, p1: String?, p2: Any?, p3: Any?) {}
    override fun debug(p0: Marker?, p1: String?, vararg p2: Any?) {}
    override fun debug(p0: Marker?, p1: String?, p2: Throwable?) {}
    override fun isInfoEnabled(): Boolean  = false
    override fun isInfoEnabled(p0: Marker?): Boolean = false
    override fun info(p0: String?) {}
    override fun info(p0: String?, p1: Any?) {}
    override fun info(p0: String?, p1: Any?, p2: Any?) {}
    override fun info(p0: String?, vararg p1: Any?) {}
    override fun info(p0: String?, p1: Throwable?) {}
    override fun info(p0: Marker?, p1: String?) {}
    override fun info(p0: Marker?, p1: String?, p2: Any?) {}
    override fun info(p0: Marker?, p1: String?, p2: Any?, p3: Any?) {}
    override fun info(p0: Marker?, p1: String?, vararg p2: Any?) {}
    override fun info(p0: Marker?, p1: String?, p2: Throwable?) {}
    override fun isWarnEnabled(): Boolean = false
    override fun isWarnEnabled(p0: Marker?): Boolean = false
    override fun warn(p0: String?) {}
    override fun warn(p0: String?, p1: Any?) {}
    override fun warn(p0: String?, vararg p1: Any?) {}
    override fun warn(p0: String?, p1: Any?, p2: Any?) {}
    override fun warn(p0: String?, p1: Throwable?) {}
    override fun warn(p0: Marker?, p1: String?) {}
    override fun warn(p0: Marker?, p1: String?, p2: Any?) {}
    override fun warn(p0: Marker?, p1: String?, p2: Any?, p3: Any?) {}
    override fun warn(p0: Marker?, p1: String?, vararg p2: Any?) {}
    override fun warn(p0: Marker?, p1: String?, p2: Throwable?) {}
    override fun isErrorEnabled(): Boolean = false
    override fun isErrorEnabled(p0: Marker?): Boolean = false
    override fun error(p0: String?) {}
    override fun error(p0: String?, p1: Any?) {}
    override fun error(p0: String?, p1: Any?, p2: Any?) {}
    override fun error(p0: String?, vararg p1: Any?) {}
    override fun error(p0: String?, p1: Throwable?) {}
    override fun error(p0: Marker?, p1: String?) {}
    override fun error(p0: Marker?, p1: String?, p2: Any?) {}
    override fun error(p0: Marker?, p1: String?, p2: Any?, p3: Any?) {}
    override fun error(p0: Marker?, p1: String?, vararg p2: Any?) {}
    override fun error(p0: Marker?, p1: String?, p2: Throwable?) {}
}
