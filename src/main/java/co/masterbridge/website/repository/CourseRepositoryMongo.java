package co.masterbridge.website.repository;

import co.masterbridge.website.model.Course;
import co.masterbridge.website.model.CourseSearch;
import co.masterbridge.website.util.MongoUtil;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public class CourseRepositoryMongo implements CourseRepository {

    private MongoCollection<Document> courseCol;

    public CourseRepositoryMongo() {

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("masterbridge-website");
        this.courseCol = database.getCollection("courses");
    }

    @Override
    public void create(Course course) {

        Document courseDoc = MongoUtil.doc()
                .append("country", course.getCountry())
                .append("field", course.getFieldOfStudy());

        courseCol.insertOne(courseDoc);
    }

    @Override
    public void update(Course course) {
    }

    @Override
    public Course getById(long id) {
        return null;
    }

    @Override
    public Collection<Course> getAll() {
        return null;
    }

    @Override
    public Collection<Course> find(CourseSearch courseSearch) {
        return null;
    }

    @Override
    public void remove(Course course) {

    }

}
