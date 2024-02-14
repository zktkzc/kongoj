import ACCESS_ENUM from "@/access/accessEnum";

/**
 * 检查权限
 * @param loginUser 当前登录用户
 * @param needAccess 需要的权限
 * @return 是否有权限
 */
const checkAccess = (
  loginUser: any,
  needAccess = ACCESS_ENUM.NOT_LOGIN
): boolean => {
  const loginUserAccess = loginUser?.userRole ?? ACCESS_ENUM.NOT_LOGIN;
  if (needAccess === ACCESS_ENUM.NOT_LOGIN) return true;
  else if (loginUserAccess === ACCESS_ENUM.USER) {
    if (loginUserAccess === ACCESS_ENUM.NOT_LOGIN) return false;
  } else if (needAccess === ACCESS_ENUM.ADMIN) {
    if (loginUserAccess !== ACCESS_ENUM.ADMIN) return false;
  }
  return true;
};

export default checkAccess;
