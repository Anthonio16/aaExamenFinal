package aaBusinessLogic;
//Refactorización

import aaBusinessLogic.Helpers.aaExobotBL;
import aaBusinessLogic.Helpers.aaAlumnoBL;
import aaInfrastructure.aaAppException;

public class FactoryBL {
    public static aaAlumnoBL aaAlumnoBL() throws aaAppException { return new aaAlumnoBL(); }
    public static aaExobotBL aaExobotBL() throws aaAppException { return new aaExobotBL(); }
}