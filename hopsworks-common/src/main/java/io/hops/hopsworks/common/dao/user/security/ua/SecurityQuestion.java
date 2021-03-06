/*
 * Changes to this file committed after and not including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * This file is part of Hopsworks
 * Copyright (C) 2018, Logical Clocks AB. All rights reserved
 *
 * Hopsworks is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Hopsworks is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 *
 * Changes to this file committed before and including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * Copyright (C) 2013 - 2018, Logical Clocks AB and RISE SICS AB. All rights reserved
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.hops.hopsworks.common.dao.user.security.ua;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "SecurityQuestion")
@XmlEnum
public enum SecurityQuestion {

  @XmlEnumValue("Mother's maiden name?")
  MAIDEN_NAME("Mother's maiden name?"),
  @XmlEnumValue("Name of your first pet?")
  PET("Name of your first pet?"),
  @XmlEnumValue("Name of your first love?")
  LOVE("Name of your first love?"),
  @XmlEnumValue("What was your childhood nickname?")
  NICK_NAME("What was your childhood nickname?"),
  @XmlEnumValue("What is the name of your favorite childhood friend?")
  CHILDHOOD_FRIEND("What is the name of your favorite childhood friend?"),
  @XmlEnumValue("What street did you live on in third grade?")
  STREET("What street did you live on in third grade?"),
  @XmlEnumValue("What is your oldest sibling's middle name?")
  SIBLING_MAIDEN_NAME("What is your oldest sibling's middle name?"),
  @XmlEnumValue("What school did you attend for sixth grade?")
  SCHOOL("What school did you attend for sixth grade?"),
  @XmlEnumValue("What is your oldest cousin's first and last name?")
  COUSIN_MAIDEN_NAME("What is your oldest cousin's first and last name?"),
  @XmlEnumValue("What was the name of your first stuffed animal?")
  STUFFED_ANIMAL("What was the name of your first stuffed animal?"),
  @XmlEnumValue("In what city does your nearest sibling live?")
  SIBLING_LIVE("In what city does your nearest sibling live?"),
  @XmlEnumValue("In what city or town was your first job?")
  FIRST_JOB_TOWN("In what city or town was your first job?");

  private final String value;

  private SecurityQuestion(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static SecurityQuestion getQuestion(String text) {
    if (text != null) {
      for (SecurityQuestion b : SecurityQuestion.values()) {
        if (text.equalsIgnoreCase(b.value)) {
          return b;
        }
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return value;
  }

  private static final List<SecurityQuestion> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static SecurityQuestion randomQuestion() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}
