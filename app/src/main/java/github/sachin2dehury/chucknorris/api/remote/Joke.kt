package github.sachin2dehury.chucknorris.api.remote

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "db")
data class Joke(
    @SerializedName("categories") val categories: List<String>,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("icon_url") val icon_url: String,
    @SerializedName("id") val id: String,
    @SerializedName("updated_at") val updated_at: String,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeInt(flags)
            writeList(categories)
            writeString(created_at)
            writeString(icon_url)
            writeString(id)
            writeString(updated_at)
            writeString(url)
            writeString(value)
        }
    }

    companion object CREATOR : Parcelable.Creator<Joke> {
        override fun createFromParcel(parcel: Parcel): Joke {
            return Joke(parcel)
        }

        override fun newArray(size: Int): Array<Joke?> {
            return arrayOfNulls(size)
        }
    }
}