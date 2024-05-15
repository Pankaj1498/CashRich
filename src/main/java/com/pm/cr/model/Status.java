package com.pm.cr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String timestamp;
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public int getError_code() {
			return error_code;
		}
		public void setError_code(int error_code) {
			this.error_code = error_code;
		}
		public String getError_message() {
			return error_message;
		}
		public void setError_message(String error_message) {
			this.error_message = error_message;
		}
		public int getElapsed() {
			return elapsed;
		}
		public void setElapsed(int elapsed) {
			this.elapsed = elapsed;
		}
		public int getCredit_count() {
			return credit_count;
		}
		public void setCredit_count(int credit_count) {
			this.credit_count = credit_count;
		}
		public String getNotice() {
			return notice;
		}
		public void setNotice(String notice) {
			this.notice = notice;
		}
		private int error_code;
	    private String error_message;
	    private int elapsed;
	    private int credit_count;
	    private String notice;
}
