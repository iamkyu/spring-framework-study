package springbook.user.service;

import springbook.user.domain.User;

/**
 * @author Kj Nam
 * @since 2016-12-01
 */
public interface UserLevelUpgradedPolicy {
    void upgradeLevel(User user);

    boolean canUpgradeLevel(User user);
}
