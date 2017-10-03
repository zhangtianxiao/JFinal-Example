package cn.zhangtianxiao.jf_example._model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseArticle<M extends BaseArticle<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.String getId() {
		return get("id");
	}

	public M setTitle(java.lang.String title) {
		set("title", title);
		return (M)this;
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public M setCatagory(java.lang.Long catagory) {
		set("catagory", catagory);
		return (M)this;
	}

	public java.lang.Long getCatagory() {
		return get("catagory");
	}

	public M setCreateUserId(java.lang.String createUserId) {
		set("create_user_id", createUserId);
		return (M)this;
	}

	public java.lang.String getCreateUserId() {
		return get("create_user_id");
	}

	public M setFilePath(java.lang.String filePath) {
		set("file_path", filePath);
		return (M)this;
	}

	public java.lang.String getFilePath() {
		return get("file_path");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setSeen(java.lang.Boolean seen) {
		set("seen", seen);
		return (M)this;
	}

	public java.lang.Boolean getSeen() {
		return get("seen");
	}

	public M setClickCount(java.lang.Long clickCount) {
		set("click_count", clickCount);
		return (M)this;
	}

	public java.lang.Long getClickCount() {
		return get("click_count");
	}

}
