package com.macower.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "tb_news_comments")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsComments extends BaseEntity{
	
	private static final long serialVersionUID = -1643047041202896272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="news_id")
	private Long newsId ;
	
	@Formula("(select t.title from tb_news t where t.id = news_id)")
	private String newsTitle ;
	
	@Column(name="member_id")
	private Long memberId ;
	
	@Formula("(select t.email from tb_member t where t.id = member_id)")
	private String memberName ;
	
	@Column(name="context")
	private String context ;
	
	@Column(name="comment_tm")
	private Date commentTm ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCommentTm() {
		return commentTm;
	}

	public void setCommentTm(Date commentTm) {
		this.commentTm = commentTm;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	

}
