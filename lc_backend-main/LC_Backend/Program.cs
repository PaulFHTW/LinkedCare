using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Npgsql;
using Microsoft.AspNetCore.Builder;

namespace LC_Backend
{
    public class Program
    {
        public static void Main(string[] args)
        {
            using var conn = new NpgsqlConnection("Server=localhost;Port=5432;User Id=postgres;Password=postgres;Database=users;");
            conn.Open();
            using var cmd = new NpgsqlCommand("SELECT * FROM USERS;", conn);
            using var reader = cmd.ExecuteReader();
            while(reader.Read()){
                var val = reader[0].ToString();
                System.Console.WriteLine(val);
            }
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                    webBuilder.UseUrls("http://localhost:5005");
                });
    }
}
