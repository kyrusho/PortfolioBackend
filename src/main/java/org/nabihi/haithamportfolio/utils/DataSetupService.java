package org.nabihi.haithamportfolio.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.UserSubdomain.DataLayer.User;
import org.nabihi.haithamportfolio.UserSubdomain.DataLayer.UserRepository;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.datalayer.CommentRepository;
import org.nabihi.haithamportfolio.me.datalayer.Me;
import org.nabihi.haithamportfolio.me.datalayer.MeRepository;
import org.nabihi.haithamportfolio.projects.datalayer.Project;
import org.nabihi.haithamportfolio.projects.datalayer.ProjectRepository;
import org.nabihi.haithamportfolio.skill.datalayer.Skill;
import org.nabihi.haithamportfolio.skill.datalayer.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {
    private final MeRepository meRepository;
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepo;
    private final SkillRepository skillRepo;
    @Override
    public void run(String... args) throws Exception{
        setupMe();
        setupComments();
        setupProject();
        setupUsers();
        setupSkills();
    }

    private Me buildMe(String meId, String firstname, String lastname, int age, String origins) {
        Me me = new Me();
        me.setMeId(meId);
        me.setFirstname(firstname);
        me.setLastname(lastname);
        me.setAge(age);
        me.setOrigins(origins);
        return me;
    }

    private Project buildProject(String projectId, String projectName, String iconUrl, String gitRepo, List<Skill> skills) {
        return Project.builder()
                .projectId(projectId)
                .projectName(projectName)
                .iconUrl(iconUrl)
                .gitRepo(gitRepo)
                .skills(skills)
                .build();
    }


    private void setupMe() {
        List<Me> me = List.of(
               buildMe("002", "Haitham", "Nabihi", 20, "Moroccan-Canadian")
        );

        Flux.fromIterable(me)
                .flatMap(me1 -> meRepository.findMeByMeId(me1.getMeId())
                        .switchIfEmpty(meRepository.insert(me1)))
                .subscribe();
    }

    private void setupProject() {

        Project project1 = buildProject(
                "projectId1",
                "Nouilles Star",
                "https://i.postimg.cc/jdW1329Z/noodle-Star.png",
                "https://github.com/Sunveerg/Noodle-Star",
                List.of(
                        Skill.builder().skillId("skillId1").skillName("Java").skillLogo("https://i.postimg.cc/8CHJ8130/java.png").build(),
                        Skill.builder().skillId("skillId2").skillName("Spring Boot").skillLogo("https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg").build(),
                        Skill.builder().skillId("skillId3").skillName("React").skillLogo("https://i.postimg.cc/B67j6mJ5/atom.png").build(),
                        Skill.builder().skillId("skillId4").skillName("TypeScript").skillLogo("https://i.postimg.cc/SKDsTgY0/typescript.png").build(),
                        Skill.builder().skillId("skillId5").skillName("MongoDb").skillLogo("https://i.postimg.cc/HWKJ1vZd/database.png").build(),
                        Skill.builder().skillId("skillId6").skillName("Git").skillLogo("https://i.postimg.cc/B6n84rfQ/git.png").build()
                )
        );

        Project project2 = buildProject(
                "projectId1",
                "Champlain PetClinic",
                "https://i.postimg.cc/nzRd0fGy/champlain-Pet-Clinic.png",
                "https://github.com/cgerard321/champlain_petclinic",
                List.of(
                        Skill.builder().skillId("skillId1").skillName("Java").skillLogo("https://i.postimg.cc/8CHJ8130/java.png").build(),
                        Skill.builder().skillId("skillId2").skillName("Spring Boot").skillLogo("https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg").build(),
                        Skill.builder().skillId("skillId3").skillName("React").skillLogo("https://i.postimg.cc/B67j6mJ5/atom.png").build(),
                        Skill.builder().skillId("skillId4").skillName("TypeScript").skillLogo("https://i.postimg.cc/SKDsTgY0/typescript.png").build(),
                        Skill.builder().skillId("skillId5").skillName("MongoDb").skillLogo("https://i.postimg.cc/HWKJ1vZd/database.png").build(),
                        Skill.builder().skillId("skillId6").skillName("Git").skillLogo("https://i.postimg.cc/B6n84rfQ/git.png").build()
                )
        );

        Project project3 = buildProject(
                "projectId3",
                "Pokemon World",
                "https://i.postimg.cc/gJTD3qYd/pokemon.png",
                "https://github.com/kyrusho/PokemonWorld",
                List.of(
                        Skill.builder().skillId("skillId1").skillName("Java").skillLogo("https://i.postimg.cc/8CHJ8130/java.png").build(),
                        Skill.builder().skillId("skillId2").skillName("Spring Boot").skillLogo("https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg").build(),
                        Skill.builder().skillId("skillId3").skillName("React").skillLogo("https://i.postimg.cc/B67j6mJ5/atom.png").build(),
                        Skill.builder().skillId("skillId4").skillName("Javascript").skillLogo("https://i.postimg.cc/4xvntfJx/js.png").build(),
                        Skill.builder().skillId("skillId5").skillName("MySQL").skillLogo("https://i.postimg.cc/qvNPBnff/mysql.png").build()
                )
        );

        Flux.just(project1, project2, project3)
                .flatMap(project -> projectRepository.findByProjectId(project.getProjectId())
                        .switchIfEmpty(Mono.defer(() -> {
                            System.out.println("Inserting project: " + project.getProjectId());
                            return projectRepository.save(project);
                        }))
                )
                .subscribe();

    }

    private void setupSkills() {
        Skill java = buildSkill("skillId1", "Java", "https://i.postimg.cc/8CHJ8130/java.png");
        Skill springBoot = buildSkill("skillId2", "Spring Boot", "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg");
        Skill react = buildSkill("skillId3", "React", "https://i.postimg.cc/B67j6mJ5/atom.png");
        Skill typescript = buildSkill("skillId4", "TypeScript", "https://i.postimg.cc/SKDsTgY0/typescript.png");
        Skill mongodb = buildSkill("skillId5", "MongoDb", "https://i.postimg.cc/HWKJ1vZd/database.png");
        Skill javascript = buildSkill("skillId6", "JavaScript", "https://i.postimg.cc/4xvntfJx/js.png");
        Skill mysql = buildSkill("skillId7", "Mysql", "https://i.postimg.cc/qvNPBnff/mysql.png");
        Skill csharp = buildSkill("skillId8", "C#", "https://i.postimg.cc/d3zDRDkL/c-sharp.png");
        Skill python= buildSkill("skillId12", "Python", "https://i.postimg.cc/1XgfNDzk/python.png");

        Flux.just(java, springBoot, react, typescript, mongodb, javascript, mysql, csharp, python)
                .flatMap(skill -> skillRepo.findSkillById(skill.getSkillId())
                        .switchIfEmpty(Mono.defer(() -> {
                            System.out.println("Inserting skill: " + skill.getSkillId());
                            return skillRepo.save(skill);
                        }))
                )
                .subscribe();
    }

    private Skill buildSkill(String skillId, String skillName, String skillLogo) {
        return Skill.builder()
                .skillId(skillId)
                .skillName(skillName)
                .skillLogo(skillLogo)
                .build();
    }


    private void setupComments() {
        List<Comment> comments = List.of(
                new Comment( "1",  "John Doe", "Great portfolio!", LocalDateTime.now(), true),
                new Comment( "2", "Jane Smith", "Impressive work!", LocalDateTime.now(), true)
        );

        Flux.fromIterable(comments)
                .flatMap(commentRepository::save)
                .subscribe();
    }

    private void setupUsers() {
        User user3 = buildUser("kyrusho", "jshn@hotmail.com", "Nabihi", "Haitham", List.of("Haitham"), null);
        User user5 = buildUser("user", "samantha@example.com", "Samantha", "Lee", List.of("User"), List.of("read"));
        Flux.just( user3, user5)
                .flatMap(user -> {
                    System.out.println("Checking if user exists: " + user.getUserId());

                    // Check if the user already exists by userId (or email)
                    return userRepo.findByUserId(user.getUserId()) // Assuming userId is the unique identifier
                            .doOnTerminate(() -> System.out.println("Terminated: " + user.getUserId()))
                            .switchIfEmpty(Mono.defer(() -> {
                                System.out.println("Inserting user: " + user.getUserId());
                                return userRepo.save(user); // Save if user doesn't exist
                            }));
                })
                .subscribe();
    }


    private User buildUser(String userId, String email, String firstName, String lastName, List<String> roles, List<String> permissions) {
        return User.builder()
                .userId(userId)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .roles(roles)
                .permissions(permissions)
                .build();
    }
}
