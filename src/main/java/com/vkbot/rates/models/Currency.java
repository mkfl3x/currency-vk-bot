package com.vkbot.rates.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Currency {

    @SerializedName("ID")
    public String iD;

    @SerializedName("NumCode")
    public String numCode;

    @SerializedName("CharCode")
    public String charCode;

    @SerializedName("Nominal")
    public Integer nominal;

    @SerializedName("Name")
    public String name;

    @SerializedName("Value")
    public Double value;

    @SerializedName("Previous")
    public Double previous;
}
