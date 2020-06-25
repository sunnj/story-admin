package com.story.storyadmin.service.common;

public interface SyncCacheService {

	Boolean getLock(String lockName, int expireTime);

	Boolean releaseLock(String lockName);
}
