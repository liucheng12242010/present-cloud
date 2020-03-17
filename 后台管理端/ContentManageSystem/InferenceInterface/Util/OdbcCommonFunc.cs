using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace ContentManageSystem.Util
{
    public class OdbcCommonFunc
    {
        public static List<T> GetTentityListByDataRow<T>(DataSet ds)
        {
            var vwList = new List<T>();
            try
            {
                foreach (DataRow dr in ds.Tables[0].Rows)
                {
                    var type = typeof (T);
                    var vwPetDomp = type.Assembly.CreateInstance(type.FullName);
                    var ps = type.GetProperties();
                    var count = ps.Count();
                    for (var i = 0; i < count; i++)
                    {
                        var valueTemp = dr.ItemArray[i].ToString();
                        var np = type.GetProperty(ps[i].Name.ToUpper());
                        if (valueTemp.ToLower() == "null")
                            continue;

                        if (!string.IsNullOrEmpty(valueTemp) && ps[i].PropertyType == typeof (string))
                        {
                            np.SetValue(vwPetDomp, valueTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (DateTime?) || ps[i].PropertyType == typeof (DateTime)))
                        {
                            var dateTemp = Convert.ToDateTime(valueTemp);
                            np.SetValue(vwPetDomp, dateTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (long?) || ps[i].PropertyType == typeof (long)))
                        {
                            var numTemp = Convert.ToInt64(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (int?) || ps[i].PropertyType == typeof (int)))
                        {
                            var numTemp = Convert.ToInt32(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (short?) || ps[i].PropertyType == typeof (short)))
                        {
                            var numTemp = Convert.ToInt16(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (double?) || ps[i].PropertyType == typeof (double)))
                        {
                            var numTemp = Convert.ToDouble(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (float?) || ps[i].PropertyType == typeof (float)))
                        {
                            var numTemp = Convert.ToSingle(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (long?) || ps[i].PropertyType == typeof (long)))
                        {
                            var numTemp = Convert.ToDouble(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (byte?) || ps[i].PropertyType == typeof (byte)))
                        {
                            var numTemp = Convert.ToByte(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (decimal?) || ps[i].PropertyType == typeof (decimal)))
                        {                   
                            var numTemp = Convert.ToDecimal(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                    }
                    vwList.Add((T) vwPetDomp);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
            return vwList;
        }

        public static T GetTentityByDataRow<T>(DataSet ds)
        {
            var type = typeof (T);
            var vwPetDomp = type.Assembly.CreateInstance(type.FullName);
            try
            {
                foreach (DataRow dr in ds.Tables[0].Rows)
                {
                    var ps = type.GetProperties();
                    var count = ps.Count();
                    for (var i = 0; i < count; i++)
                    {
                        var valueTemp = dr.ItemArray[i].ToString();
                        var np = type.GetProperty(ps[i].Name.ToUpper());
                        if (!string.IsNullOrEmpty(valueTemp) && ps[i].PropertyType == typeof (string))
                        {
                            np.SetValue(vwPetDomp, valueTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (DateTime?) || ps[i].PropertyType == typeof (DateTime)))
                        {
                            var dateTemp = Convert.ToDateTime(valueTemp);
                            np.SetValue(vwPetDomp, dateTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (long?) || ps[i].PropertyType == typeof (long)))
                        {
                            var numTemp = Convert.ToInt64(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (int?) || ps[i].PropertyType == typeof (int)))
                        {
                            var numTemp = Convert.ToInt32(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (short?) || ps[i].PropertyType == typeof (short)))
                        {
                            var numTemp = Convert.ToInt16(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                            continue;
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (double?) || ps[i].PropertyType == typeof (double)))
                        {
                            var numTemp = Convert.ToDouble(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (float?) || ps[i].PropertyType == typeof (float)))
                        {
                            var numTemp = Convert.ToSingle(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (long?) || ps[i].PropertyType == typeof (long)))
                        {
                            var numTemp = Convert.ToDouble(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (byte?) || ps[i].PropertyType == typeof (byte)))
                        {
                            var numTemp = Convert.ToByte(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                        if (!string.IsNullOrEmpty(valueTemp) &&
                            (ps[i].PropertyType == typeof (decimal?) || ps[i].PropertyType == typeof (decimal)))
                        {
                            var numTemp = Convert.ToDecimal(valueTemp);
                            np.SetValue(vwPetDomp, numTemp);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
            return (T) vwPetDomp;
        }
    }
}