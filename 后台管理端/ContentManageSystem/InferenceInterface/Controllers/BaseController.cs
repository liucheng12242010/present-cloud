using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ContentManageSystem.Controllers
{
    public class BaseController : Controller
    {
        //单点登录
        protected override void OnActionExecuting(ActionExecutingContext filterContext)
        {
            //bool isDevMode = true;
            //if (isDevMode == true)
            //{
            //    Session["login_name"] = "admin";
            //}
            ////判断是否有会话信息
            //if (Session["login_name"] == null && isDevMode != true)
            //{
            //    Response.Redirect("User/Login");
            //}
            //else
            //{
            //    //插入访问成功日志
            //    Common.InsDBLog("Info", "", System.Web.HttpUtility.UrlDecode(filterContext.HttpContext.Request.RawUrl.ToString()),
            //                    "用户" + Session["login_name"].ToString() + "访问成功", "");
            //}
        }
        //转发
        private void Redirect(ActionExecutingContext filterContext, string url)
        {
            ContentResult Content = new ContentResult();
            Content.Content = string.Format("<script type='text/javascript'>window.location.href='{0}';</script>", url);
            filterContext.Result = Content;
        }
        //单点登录&登出URL
        //private string logInHost = System.Configuration.ConfigurationManager.AppSettings["SSO_URL"];
    }
}