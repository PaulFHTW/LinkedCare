using Hl7.Fhir.Model;
using Hl7.Fhir.Serialization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Npgsql;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Runtime.ConstrainedExecution;
using System.Threading.Tasks;

namespace LC_Backend
{
    [ApiController]
    public class LoginController : ControllerBase
    {

        public class LoginData
        {
            public string Username { get; set; }
            public string Password { get; set; }
        }

        public class UserData
        {
            public string Username { get; set; }
            public string Firstname { get; set; }
            public string Lastname { get; set; }
            public string Role { get; set; }
            public string AssociatedId { get; set; }
        }
        [Route ("login")]
        [HttpPost]
        public UserData Login(LoginData login)
        {
            using var conn = new NpgsqlConnection("Server=172.17.0.3;Port=5432;User Id=postgres;Database=users;");
            conn.Open();
            using var cmd = new NpgsqlCommand("SELECT username, firstname, lastname, role, patientid FROM users WHERE username=@username AND password=@password;", conn);
            cmd.Parameters.AddWithValue("username", login.Username);
            cmd.Parameters.AddWithValue("password", login.Password);
            using var reader = cmd.ExecuteReader();
            reader.Read();
            var user = new UserData
            {
                Username = reader.IsDBNull(0) ? null : reader.GetString(0),
                Firstname = reader.IsDBNull(1) ? null : reader.GetString(1),
                Lastname = reader.IsDBNull(2) ? null : reader.GetString(2),
                Role = reader.IsDBNull(3) ? null : reader.GetString(3),
                AssociatedId = reader.IsDBNull(4) ? null : reader.GetString(4),
            };
            HttpContext.Session.SetString("user", user.Username);
            return user;
        }
    }
}
