# 🏀 Basketball Club Manager

A console application for managing a basketball club's staff, payroll and weekly training schedule, written in Java.

Built as an object-oriented programming practice project.

## Features

- Manage players, head coach, assistant coaches and physiotherapists
- Club rules: max 12 players, unique jersey numbers, only one head coach
- Role-based salary calculation (performance bonus for players, experience-based for coaches, per-session for physios)
- Payroll report with budget check
- Players ranked by points per game (custom selection sort)
- Weekly training schedule (7 days × 3 slots)
- Salary projection with yearly raises (recursive)

## Concepts Used

| Concept | Where |
|---|---|
| Inheritance (2 levels) | `Employee` → `Player`, `Coach`, `Physio`; `Coach` → `HeadCoach`, `AssistantCoach` |
| Abstract classes | `Employee` and `Coach` define shared structure, subclasses define salary and role |
| Polymorphism | Payroll is calculated in one loop over all employees without type checks |
| Casting & `instanceof` | Filtering players and the head coach from the employee list |
| Custom checked exception | `RosterException` for club rule violations |
| Arrays | Position validation, sorting players by PPG |
| 2D arrays | `TrainingSchedule` stores sessions in a `String[7][3]` grid |
| Recursion | Salary projection, payroll sum, lineup combinations, player count |

## Class Structure

```
Employee (abstract)
 ├── Player
 ├── Physio
 └── Coach (abstract)
      ├── HeadCoach
      └── AssistantCoach

Club ──has──> Employee list
TrainingSchedule ──has──> String[7][3]
ClubUtils ──> recursive helper methods
RosterException extends Exception
```

## How to Run

Requires JDK 17 or newer. From the project root:

```bash
javac -d out src/*.java
java -cp out ClubApp
```

To run the test scenario:

```bash
java -cp out Main
```

## Example

```
===== CLUB MANAGER =====
1. List employees
2. Add player
3. Remove employee
4. Payroll report
5. Players by PPG
6. Training schedule
7. Salary projection
0. Exit
Choice: 5
1. Ali Kaya - 18.5 PPG
2. Can Ozturk - 14.2 PPG
3. Burak Demir - 9.0 PPG
4. Deniz Arslan - 6.5 PPG
```

```
Mon: Shooting | - | Video analysis
Tue: - | - | -
Wed: Gym | Team practice | Recovery
```