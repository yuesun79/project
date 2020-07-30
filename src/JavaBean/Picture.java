package JavaBean;

import java.sql.Timestamp;

public class Picture {
	private int imageID;
	private String title;
	private String description;
	private int cityCode;
	private String countryCodeISO;
	private int UID;
	private String path;
	private String content;
	private String likes;
//	private Timestamp timestamp;
	private Timestamp dateTime;
	
	private String asciiName;
	private String countryName;
	private String userName;
	
	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String author) {
		this.userName = author;
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAsciiName() {
		return asciiName;
	}

	public void setAsciiName(String asciiName) {
		this.asciiName = asciiName;
	}

	public String getCountryCodeISO() {
		return countryCodeISO;
	}

	public void setCountryCodeISO(String countryCodeISO) {
		this.countryCodeISO = countryCodeISO;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public Picture() {
		
	}

	public Picture(int imageID, String title, String description, int cityCode, String countryCodeISO, int uID,
			String path, String content, String likes, Timestamp dateTime, String asciiName, String countryName, String userName) {
		super();
		this.imageID = imageID;
		this.title = title;
		this.description = description;
		this.cityCode = cityCode;
		this.countryCodeISO = countryCodeISO;
		UID = uID;
		this.path = path;
		this.content = content;
		this.likes = likes;
		this.dateTime = dateTime;
		
		this.asciiName = asciiName;
		this.countryName = countryName;
		this.userName = userName;
	}
	
	public Picture(int imageID, String title, String description, int cityCode, String countryCodeISO, int uID,
			String path, String content, String likes, Timestamp dateTime) {
		super();
		this.imageID = imageID;
		this.title = title;
		this.description = description;
		this.cityCode = cityCode;
		this.countryCodeISO = countryCodeISO;
		UID = uID;
		this.path = path;
		this.content = content;
		this.likes = likes;
		this.dateTime = dateTime;
	}

	public Picture(String title, String description, int cityCode, String countryCodeISO, int uID, String path,
			String content, String likes, Timestamp dateTime) {
		super();
		this.title = title;
		this.description = description;
		this.cityCode = cityCode;
		this.countryCodeISO = countryCodeISO;
		UID = uID;
		this.path = path;
		this.content = content;
		this.likes = likes;
		this.dateTime = dateTime;
	}

	public Picture(int imageID, String title, String description, int cityCode, String countryCodeISO, String path, String content,
			Timestamp dateTime) {
		super();
		this.imageID = imageID;
		this.title = title;
		this.description = description;
		this.cityCode = cityCode;
		this.countryCodeISO = countryCodeISO;
		this.path = path;
		this.content = content;
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Picture [imageID=" + imageID + ", title=" + title + ", description=" + description + ", cityCode="
				+ cityCode + ", countryCodeISO=" + countryCodeISO + ", UID=" + UID + ", path=" + path + ", content="
				+ content + ", likes=" + likes + ", asciiName=" + asciiName + ", countryName=" + countryName
				+ ", userName=" + userName + "]";
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}


}
