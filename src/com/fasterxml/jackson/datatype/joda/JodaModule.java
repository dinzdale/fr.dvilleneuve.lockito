package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.joda.deser.DateMidnightDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeZoneDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.IntervalDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.MonthDayDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.PeriodDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateMidnightSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeZoneSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DurationSerializer;
import com.fasterxml.jackson.datatype.joda.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.joda.ser.IntervalSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.PeriodSerializer;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MonthDay;
import org.joda.time.Period;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.YearMonth;

public class JodaModule
  extends SimpleModule
{
  private static final long serialVersionUID = 1L;
  
  public JodaModule()
  {
    super(PackageVersion.VERSION);
    addDeserializer(DateMidnight.class, new DateMidnightDeserializer());
    addDeserializer(DateTime.class, DateTimeDeserializer.forType(DateTime.class));
    addDeserializer(DateTimeZone.class, new DateTimeZoneDeserializer());
    addDeserializer(Duration.class, new DurationDeserializer());
    addDeserializer(Instant.class, new InstantDeserializer());
    addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    addDeserializer(LocalDate.class, new LocalDateDeserializer());
    addDeserializer(LocalTime.class, new LocalTimeDeserializer());
    addDeserializer(Period.class, new PeriodDeserializer());
    addDeserializer(ReadableDateTime.class, DateTimeDeserializer.forType(ReadableDateTime.class));
    addDeserializer(ReadableInstant.class, DateTimeDeserializer.forType(ReadableInstant.class));
    addDeserializer(Interval.class, new IntervalDeserializer());
    addDeserializer(MonthDay.class, new MonthDayDeserializer());
    addDeserializer(YearMonth.class, new YearMonthDeserializer());
    ToStringSerializer localToStringSerializer = ToStringSerializer.instance;
    addSerializer(DateMidnight.class, new DateMidnightSerializer());
    addSerializer(DateTime.class, new DateTimeSerializer());
    addSerializer(DateTimeZone.class, new DateTimeZoneSerializer());
    addSerializer(Duration.class, new DurationSerializer());
    addSerializer(Instant.class, new InstantSerializer());
    addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    addSerializer(LocalDate.class, new LocalDateSerializer());
    addSerializer(LocalTime.class, new LocalTimeSerializer());
    addSerializer(Period.class, new PeriodSerializer());
    addSerializer(Interval.class, new IntervalSerializer());
    addSerializer(MonthDay.class, localToStringSerializer);
    addSerializer(YearMonth.class, localToStringSerializer);
    addKeyDeserializer(DateTime.class, new DateTimeKeyDeserializer());
  }
  
  public boolean equals(Object paramObject)
  {
    return this == paramObject;
  }
  
  public int hashCode()
  {
    return getClass().hashCode();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.JodaModule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */