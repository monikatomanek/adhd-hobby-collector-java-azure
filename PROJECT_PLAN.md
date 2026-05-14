# Project Plan

## Project name

ADHD Hobby Collector

## Purpose

This project tracks hobbies, projects, supplies, costs, motivation, and project outcomes.

It is designed for people who enjoy trying many hobbies but may lose interest, abandon projects, or spend money on supplies quickly.

## Main features

- View hobbies
- View projects
- Add new projects
- Update project status
- Track supplies and costs
- Archive deleted projects
- Track motivation level
- Show simple spending and completion patterns

## Database tables

### Hobbies

Stores each hobby, its category, and skill level.

### Projects

Stores projects linked to hobbies.

Tracks:

- project name
- status
- notes
- started date
- finished date
- abandoned date
- created date
- motivation level

### Supplies

Stores supplies linked to hobbies.

Tracks:

- item name
- cost
- whether it is essential
- supply type

### DeletedProjects

Stores archived projects after they are deleted from the main Projects table.

## Planned Java version

The Java app will allow the user to interact with the database without writing SQL manually.

The first Java version will be a simple console app.

Later, it may become a Spring Boot web app connected to Azure.
