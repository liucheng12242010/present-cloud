using System;
using System.Web.Mvc;
using System.Collections.Generic;
using System.Configuration;
using Xal.Sys.Util;
using Xal.Ecode.Utils;

namespace ContentManageSystem.Controllers
{
    public class HomeController : BaseController
    {
        [HttpGet]
        public ActionResult Index()
        {
            return View();
        }
        [HttpGet]
        public ActionResult Console()
        {
            try
            {
                var mapper_Arrv = RestfulUtil.GetMethod(ConfigurationManager.AppSettings["URL_Airports"]);
                var mapper_Dept = RestfulUtil.GetMethod(ConfigurationManager.AppSettings["URL_Airports"]);
                var mapper_Waypoint = RestfulUtil.GetMethod(ConfigurationManager.AppSettings["URL_Waypoint"]);
                var arrvs = JsonHelper.Deserialize<List<string>>(mapper_Arrv);
                var depts = JsonHelper.Deserialize<List<string>>(mapper_Dept);
                var waypoints = JsonHelper.Deserialize<List<string>>(mapper_Waypoint);
                ViewData["arrvs"] = arrvs;
                ViewData["depts"] = depts;
                ViewData["waypoints"] = waypoints;
            }
            catch (Exception e)
            {
                  
            }
            return View();
        }
    }
}