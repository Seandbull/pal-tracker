package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main (String[] args){
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public TimeEntryRepository timeEntryRepository (DataSource dataSource){
        return new JdbcTimeEntryRepository (dataSource);
    }


    @Component
    public class TotalUsersInfoContributor implements InfoContributor {



        @Override
        public void contribute(Info.Builder builder) {
            Map<String, String> appDetails = new HashMap<>();
            appDetails.put("name","Pal Traker 500000" );
            appDetails.put("Source","This came from Override" );
            appDetails.put("git branch", System.getenv("GIT_BRANCH"));
            appDetails.put("git user name", System.getenv("GIT_USER_NAME"));
            builder.withDetail("MyApp", appDetails);
        }
    }



}