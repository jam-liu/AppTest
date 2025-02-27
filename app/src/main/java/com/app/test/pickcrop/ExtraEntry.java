/*
 * Copyright (c) 2015 mariotaku
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.test.pickcrop;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public final class ExtraEntry implements Parcelable {

    public static final Creator<ExtraEntry> CREATOR = new Creator<ExtraEntry>() {
        @Override
        public ExtraEntry createFromParcel(final Parcel source) {
            return new ExtraEntry(source);
        }

        @Override
        public ExtraEntry[] newArray(final int size) {
            return new ExtraEntry[0];
        }
    };

    public String name;
    public String value;
    public int result;

    public ExtraEntry(final String name, final String value, final int result) {
        this.name = name;
        this.value = value;
        this.result = result;
    }

    public ExtraEntry(final Parcel source) {
        name = source.readString();
        value = source.readString();
        result = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeString(value);
        dest.writeInt(result);
    }
}
