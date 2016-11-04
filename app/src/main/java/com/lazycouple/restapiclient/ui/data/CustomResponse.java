package com.lazycouple.restapiclient.ui.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by noco on 2016-10-26.
 */
public class CustomResponse implements Parcelable {
    public int code;
    public String body;
    public String errorbody;

    public CustomResponse() {
    }

    protected CustomResponse(Parcel in) {
        code = in.readInt();
        body = in.readString();
        errorbody = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(body);
        dest.writeString(errorbody);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomResponse> CREATOR = new Creator<CustomResponse>() {
        @Override
        public CustomResponse createFromParcel(Parcel in) {
            return new CustomResponse(in);
        }

        @Override
        public CustomResponse[] newArray(int size) {
            return new CustomResponse[size];
        }
    };


    public static final class CustomResponseBuilder {
        private String errorbody;
        private int code;
        private String body;

        public CustomResponseBuilder() {
        }

        public static CustomResponseBuilder aCustomResponse() {
            return new CustomResponseBuilder();
        }

        public CustomResponseBuilder errorbody(String errorbody) {
            this.errorbody = errorbody;
            return this;
        }

        public CustomResponseBuilder code(int code) {
            this.code = code;
            return this;
        }

        public CustomResponseBuilder body(String body) {
            this.body = body;
            return this;
        }

        public CustomResponse build() {
            CustomResponse customResponse = new CustomResponse();
            customResponse.body = this.body;
            customResponse.code = this.code;
            customResponse.errorbody = this.errorbody;
            return customResponse;
        }
    }
}
