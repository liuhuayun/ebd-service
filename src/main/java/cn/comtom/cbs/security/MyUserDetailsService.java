/**
 * 
 */
package cn.comtom.cbs.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cn.comtom.cbs.model.User;
import cn.comtom.cbs.service.UserService;

/**
 * SocialUserDetailsService,loadUserByUserId实现根据userID查找用户信息
 * @author william
 *
 */
@Component
// @Primary
public class MyUserDetailsService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Resource
	private UserService userService;

	@Autowired
	HttpServletRequest request;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户:" + username);

		String password = request.getParameter("password");

		logger.info("密码加密后为:" + passwordEncoder.encode(password));
		User user = userService.findByUserName(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					true, true, true, true,
					AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
		}

		return null;
	}

}
