package com.jeecms.bbs.entity;

import static com.jeecms.bbs.Constants.DAY_MILLIS;
import static com.jeecms.common.web.Constants.INDEX;

import org.apache.commons.lang.StringUtils;

import com.jeecms.bbs.entity.base.BaseBbsForum;

public class BbsForum extends BaseBbsForum {
	private static final long serialVersionUID = 1L;

	public void init() {
		if (getPointPrime() == null) {
			setPointPrime(0);
		}
		if (getPointReply() == null) {
			setPointReply(0);
		}
		if (getPointTopic() == null) {
			setPointTopic(0);
		}
		if (getPostToday() == null) {
			setPostToday(0);
		}
		if (getPostTotal() == null) {
			setPostTotal(0);
		}
		if (getTopicLockLimit() == null) {
			setTopicLockLimit(0);
		}
		if (getTopicTotal() == null) {
			setTopicTotal(0);
		}
		if(getPrestigeAvailable()==null){
			setPrestigeAvailable(true);
		}
		if(getPointAvailable()==null){
			setPointAvailable(true);
		}
	}

	/**
	 * 获得访问路径。如：http://bbs.jeecms.com/luntan/index.htm
	 * 
	 * @return
	 */
	public String getUrl() {
		String url = getOuterUrl();
		if (!StringUtils.isBlank(url)) {
			// 外部链接
			if (url.startsWith("http://")) {
				return url;
			} else if (url.startsWith("/")) {
				return getSite().getUrl() + url;
			} else {
				return "http://" + url;
			}
		} else {
			return getSite().getUrlBuffer(true, null, false).append("/")
					.append(getPath()).append("/").append(INDEX).append(
							getSite().getDynamicSuffix()).toString();
		}
	}

	public String getRedirectUrl() {
		String url = "/" + getPath() + "/" + INDEX
				+ getSite().getDynamicSuffix();
		return url;
	}

	/**
	 * 是否锁定主题
	 * 
	 * @param time
	 *            主题发表时间
	 * @return
	 */
	public boolean isTopicLock(long time) {
		if (getTopicLockLimit() == 0) {
			return false;
		}
		return System.currentTimeMillis() - time > getTopicLockLimit()
				* DAY_MILLIS;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsForum() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsForum(Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsForum(Integer id,
			com.jeecms.bbs.entity.BbsCategory category,
			com.jeecms.core.entity.CmsSite site, String path,
			String title, Integer topicLockLimit,
			Integer priority, Integer topicTotal,
			Integer postTotal, Integer postToday,
			Integer pointTopic, Integer pointReply,
			Integer pointPrime) {

		super(id, category, site, path, title, topicLockLimit, priority,
				topicTotal, postTotal, postToday, pointTopic, pointReply,
				pointPrime);
	}

	/* [CONSTRUCTOR MARKER END] */

}