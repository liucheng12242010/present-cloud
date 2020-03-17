using System;
using System.Collections.Generic;
using System.Data;
using System.Globalization;
using Oracle.ManagedDataAccess.Client;

namespace ContentManageSystem.Util
{
    public class GsdevDBOdbc
    {
        public static string ConString = System.Configuration.ConfigurationManager.AppSettings["DbConfig"].ToString();
        private readonly IFormatProvider _culture = new CultureInfo("en-US");
        public OracleConnection FocConn = new OracleConnection(ConString);
    }
}